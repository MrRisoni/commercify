<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopCategoriesBelongs extends AbstractMigration
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
        $shopBelongsCats = $this->table('shop_belongs_categories', ['signed' => false]);
        $shopBelongsCats->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('category_id', 'biginteger', ['signed' => false])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('category_id', 'shop_categories', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
