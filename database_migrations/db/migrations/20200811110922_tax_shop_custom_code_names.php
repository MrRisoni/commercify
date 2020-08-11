<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TaxShopCustomCodeNames extends AbstractMigration
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

        // bind all taxes to this table
        // and bind all that to product categories
        $taxCustomCodeNames = $this->table('shop_tax_code_names', ['signed' => false]);
        $taxCustomCodeNames->addColumn('shop_id', 'biginteger', ['signed' => false])
        ->addColumn('code', 'string', ['limit' => 250])
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addIndex(['shop_id', 'code'], ['unique' => true])
        ->create();
    }
}
