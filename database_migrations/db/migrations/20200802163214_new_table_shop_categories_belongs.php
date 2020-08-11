<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopCategoriesBelongs extends AbstractMigration
{

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
