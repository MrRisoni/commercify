<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProducts extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        // for bigint search MySQLAdapter
        $products = $this->table('products', ['signed' => false]);
        $products->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('category_id', 'biginteger', ['signed' => false])
            ->addColumn('currency_id', 'biginteger', ['signed' => false])
            ->addColumn('code', 'string', ['limit' => 120])
            ->addColumn('price', 'decimal', ['precision' => 10,'scale'=>2])
            ->addColumn('discount_percent', 'decimal', ['precision' => 5,'scale'=>2])
            ->addColumn('kilos', 'decimal', ['precision' => 5,'scale'=>2])
            ->addColumn('dim_l', 'decimal', ['precision' => 6,'scale'=>2])
            ->addColumn('dim_w', 'decimal', ['precision' => 6,'scale'=>2])
            ->addColumn('dim_h', 'decimal', ['precision' => 6,'scale'=>2])
            ->addColumn('active', 'boolean')
            ->addColumn('stock', 'biginteger', ['signed' => false])
            ->addColumn('created', 'datetime')
            ->addColumn('updated', 'datetime', ['null' => true])
            ->addIndex(['shop_id', 'code'], ['unique' => true])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('category_id', 'product_categories', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('currency_id', 'currencies', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();

    }
}
